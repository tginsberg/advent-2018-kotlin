/*
 * Copyright (c) 2018 by Todd Ginsberg
 */

/**
 * Advent of Code 2018, Day 24 - Immune System Simulator 20XX
 *
 * Problem Description: http://adventofcode.com/2018/day/24
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2018/day24/
 */
package com.ginsberg.advent2018


class Day24(immuneInput: List<String>, infectionInput: List<String>) {

    private val fighters: List<Group> = immuneInput.map { Group.of(Team.ImmuneSystem, it) } + infectionInput.map { Group.of(Team.Infection, it) }

    fun solvePart1(): Int =
        fightBattle(fighters).sumBy { it.units }

    fun solvePart2(): Int =
        generateSequence(1, Int::inc).mapNotNull { boost ->
            val combatants = fightBattle(boostImmuneSystem(boost))
            if (combatants.all { it.team == Team.ImmuneSystem }) combatants.sumBy { it.units }
            else null
        }.first()

    private fun boostImmuneSystem(boost: Int): List<Group> =
        fighters.map {
            it.copy(unitDamage = it.unitDamage + if (it.team == Team.ImmuneSystem) boost else 0)
        }

    // Fight the battle with the given combatants, and return the survivors
    private fun fightBattle(combatants: List<Group>): List<Group> {
        var deaths: Int? = null
        while (deaths != 0) {
            deaths = roundOfFighting(combatants)
        }
        return combatants.filter { it.alive }
    }

    private fun roundOfFighting(combatants: List<Group>): Int =
        findTargets(combatants)
            .sortedByDescending { it.first.initiative }
            .filterNot { it.second == null }
            .map { (attacker, target) ->
                if (attacker.alive) attacker.fight(target!!)
                else 0
            }
            .sum()

    private fun findTargets(combatants: List<Group>): Set<Pair<Group, Group?>> {
        val alreadyTargeted = mutableSetOf<Group>()
        return combatants.filter { it.alive }
            .sortedWith(compareByDescending<Group> { it.effectivePower() }.thenByDescending { it.initiative })
            .map { group ->
                group to combatants
                    .filter { it.alive }   // Only fight the living
                    .filterNot { group.team == it.team }  // Only fight the other team
                    .filterNot { it in alreadyTargeted }  // Only fight somebody that isn't already a target
                    .sortedWith(compareByDescending<Group> { group.calculateDamageTo(it) }.thenByDescending { it.effectivePower() }.thenByDescending { it.initiative })
                    .filterNot { group.calculateDamageTo(it) == 0 }  // Remove any targets that we can't actually damage.
                    .firstOrNull() // Account for the fact that we *may* not have a target
                    .also {
                        // Add our selected target to the targeted list
                        if (it != null) alreadyTargeted.add(it)
                    }
            }
            .toSet()
    }

    private enum class Team {
        ImmuneSystem,
        Infection
    }

    private data class Group(
        val team: Team,
        var units: Int,
        val unitHp: Int,
        val unitDamage: Int,
        val attackType: String,
        val initiative: Int,
        val weakTo: Set<String>,
        val immuneTo: Set<String>,
        var alive: Boolean = true
    ) {
        fun effectivePower(): Int =
            units * unitDamage

        fun calculateDamageTo(other: Group): Int =
            if (this.team == other.team) 0
            else effectivePower() * when (attackType) {
                in other.immuneTo -> 0
                in other.weakTo -> 2
                else -> 1
            }

        fun fight(other: Group): Int {
            val unitDeath = calculateDamageTo(other) / other.unitHp
            other.units -= unitDeath
            if (other.units <= 0) {
                other.alive = false
            }
            return unitDeath
        }

        companion object {
            private val pattern = """^(\d+) units each with (\d+) hit points (\([,; a-z]+\) )?with an attack that does (\d+) (\w+) damage at initiative (\d+)$""".toRegex()

            fun of(team: Team, input: String): Group {
                pattern.find(input)?.let {
                    val (units, unitHp, strongWeak, unitDamage, attackType, initiative) = it.destructured
                    return Group(
                        team = team,
                        units = units.toInt(),
                        unitHp = unitHp.toInt(),
                        unitDamage = unitDamage.toInt(),
                        attackType = attackType,
                        initiative = initiative.toInt(),
                        weakTo = parseStrongWeak("weak", strongWeak),
                        immuneTo = parseStrongWeak("immune", strongWeak)
                    )
                }
            }

            private fun parseStrongWeak(kind: String, input: String): Set<String> =
                if (input.isBlank()) emptySet()
                else {
                    val found = input.drop(1).dropLast(2).split("; ").filter { it.startsWith(kind) }
                    if (found.isEmpty()) {
                        emptySet()
                    } else {
                        found.first().split("to ")[1].split(",").map { it.trim() }.toSet()
                    }
                }
        }
    }
}