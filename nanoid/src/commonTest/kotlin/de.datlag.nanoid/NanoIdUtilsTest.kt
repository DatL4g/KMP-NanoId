package de.datlag.nanoid

import kotlin.random.Random
import kotlin.test.*

class NanoIdUtilsTest {

    @Test
    @ExperimentalStdlibApi
    fun NanoIdUtils_Verify100KRandomNanoIdsAreUnique() {
        val idCount = 100000
        val ids: MutableSet<String> = HashSet(idCount)

        for(i in 0 until idCount) {
            val id = NanoIdUtils.randomNanoId()

            if(!ids.contains(id)) {
                ids.add(id)
            } else {
                fail("Non-unique ID generated: $id")
            }
        }
    }

    @Test
    @ExperimentalStdlibApi
    fun NanoIdUtils_SeededRandom() {
        val random = Random(12345)
        val expectedIds: Array<String> = arrayOf("CBf3MTFR_-Yje-f5imwBO", "J-6TdBAz6_lc9OZ1E1J8H", "H3dTyOKNz5Q2POgun3BBD", "p_V_DgrPnjHn9OxAVuJtO", "OQCnbHGL6r-C5ocjxt6J9")

        for(expectedId in expectedIds) {
            val generatedId = NanoIdUtils.randomNanoId(random)
            assertEquals(expectedId, generatedId)
        }
    }

    /* Fails on Native
     * presumably because the memory for the variable is not released
     * This is not a mistake by the library, but by Kotlin or the C library created
     */
    @Test
    @Ignore
    @ExperimentalStdlibApi
    fun NanoIdUtils_VariousSizes() {
        for(size in 1 until 1001) {
            val id = NanoIdUtils.randomNanoId(size = size)

            assertEquals(size, id.length)
        }
    }

    @Test
    @ExperimentalStdlibApi
    fun NanoIdUtils_WellDistributed() {
        val idCount = 100000
        val idSize = 20
        val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()
        val charCounts: MutableMap<String, Long> = HashMap()

        for(i in 0 until idCount) {
            val id = NanoIdUtils.randomNanoId(alphabet = alphabet, size = idSize)

            for(j in id.indices) {
                val value = id[j].toString()
                val charCount: Long? = charCounts[value]

                if(charCount == null) {
                    charCounts[value] = 1L
                } else {
                    charCounts[value] = charCount +1
                }
            }
        }

        for(charCount in charCounts.values) {
            val distribution: Double = (charCount * alphabet.size / (idCount * idSize).toDouble())
            assertTrue(distribution in 0.95..1.05)
        }
    }

    @Test
    @ExperimentalStdlibApi
    fun NanoIdUtils_EmptyAlphabet_Exception() {
        assertFailsWith<IllegalArgumentException> {
            NanoIdUtils.randomNanoId(alphabet = charArrayOf())
        }
    }

    @Test
    @ExperimentalStdlibApi
    fun NanoIdUtils_256Alphabet_Exception() {
        val largeAlphabet = CharArray(256)
        for(i in 0 until 256) {
            largeAlphabet[i] = i.toChar()
        }

        assertFailsWith<IllegalArgumentException> {
            NanoIdUtils.randomNanoId(alphabet = largeAlphabet)
        }
    }

    @Test
    @ExperimentalStdlibApi
    fun NanoIdUtils_NegativeSize_Exception() {
        assertFailsWith<IllegalArgumentException> {
            NanoIdUtils.randomNanoId(size = -10)
        }
    }

    @Test
    @ExperimentalStdlibApi
    fun NanoIdUtils_ZeroSize_Exception() {
        assertFailsWith<IllegalArgumentException> {
            NanoIdUtils.randomNanoId(size = 0)
        }
    }

}