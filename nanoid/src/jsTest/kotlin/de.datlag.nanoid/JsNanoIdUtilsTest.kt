package de.datlag.nanoid

import kotlin.random.Random
import kotlin.test.*

class JsNanoIdUtilsTest {

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

}