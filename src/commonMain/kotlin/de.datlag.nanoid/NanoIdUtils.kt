package de.datlag.nanoid

import com.soywiz.krypto.SecureRandom
import kotlin.experimental.and
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.log
import kotlin.random.Random

class NanoIdUtils private constructor() {

    companion object {
        @JvmStatic
        val DEFAULT_NUMBER_GENERATOR: SecureRandom = SecureRandom()

        @ExperimentalStdlibApi
        @JvmStatic
        val DEFAULT_ALPHABET: CharArray = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()

        const val DEFAULT_SIZE = 21

        @ExperimentalStdlibApi
        @JvmStatic
        @JvmOverloads
        fun randomNanoId(
            random: Random = DEFAULT_NUMBER_GENERATOR,
            alphabet: CharArray = DEFAULT_ALPHABET,
            size: Int = DEFAULT_SIZE
        ): String {
            if(alphabet.isEmpty() || alphabet.size >= 256) {
                throw IllegalArgumentException("Alphabet must contain between 1 and 255 symbols.")
            }
            if(size <= 0) {
                throw IllegalArgumentException("Size must be greater than zero.")
            }

            val mask: Int = (2 shl floor(log((alphabet.size - 1).toDouble(), 2.0)).toInt()) - 1
            val step: Int = ceil(1.6 * mask * size / alphabet.size).toInt()
            val idBuilder = StringBuilder()

            while (true) {
                val bytes = ByteArray(step)
                random.nextBytes(bytes)

                for(i in 0 until step) {
                    val alphabetIndex: Int = (bytes[i] and mask.toByte()).toInt()

                    if(alphabetIndex < alphabet.size) {
                        idBuilder.append(alphabet[alphabetIndex])

                        if(idBuilder.length == size) {
                            return idBuilder.toString()
                        }
                    }
                }
            }
        }
    }

}