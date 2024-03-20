package com.example.individualquiz

import kotlin.random.Random

object Composition {

    private val matrixSize = 2

    fun getQuestions(): Pair<String, Array<Array<Int>>> {
        val matrix1 = generateRandomMatrix(matrixSize)
        val matrix2 = generateRandomMatrix(matrixSize)

        val problem = "Compose matrices:\n$matrix1\n$matrix2"

        return Pair(problem, multiplyMatrices(matrix1, matrix2))
    }

    private fun generateRandomMatrix(size: Int): Array<Array<Int>> {
        val matrix = Array(size) { Array(size) { Random.nextInt(1, 10) } }
        return matrix
    }

    private fun multiplyMatrices(matrix1: Array<Array<Int>>, matrix2: Array<Array<Int>>): Array<Array<Int>> {
        val result = Array(matrix1.size) { Array(matrix2[0].size) { 0 } }

        for (i in 0 until matrix1.size) {
            for (j in 0 until matrix2[0].size) {
                for (k in 0 until matrix1[0].size) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j]
                }
            }
        }

        return result
    }

}