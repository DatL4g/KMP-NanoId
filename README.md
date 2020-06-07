# Kotlin Multiplatform NanoId

A unique string ID generator for Javascript, JVM and Native written in Kotlin.

### Secure
KMP-NanoId uses Korlibs [Krypto SecureRandom](https://github.com/korlibs/krypto) to generate cryptographically strong random IDs with a proper distribution of characters.

Krypto provides a `SecureRandom` class that extends the `kotlin.random.Random` class, but generating cryptographic secure values.

It uses `SecureRandom` on the JVM + `PRNGFixes` on Android. On Native POSIX (including Linux, macOs and iOS), it uses `/dev/urandom`, on Windows `BCryptGenRandom`

### Compact
KMP-NanoId generates compact IDs with just 21 characters.

By using a larger alphabet than UUID, KMP-NanoId can generate a greater number of unique IDs, when compared to UUID, with fewer characters (21 vs 36)

### URL-Friendly
KMP-NanoId uses URL-friendly character (`A-Za-z0-9_-`). Perfect for unique identifiers in web applications.

### Customizable
KMP-NanoId is fully customizable.

All default options may be overridden. 
Supply your own Random Number Generator, alphabet or size.

### Tested
KMP-NanoId is thoroughly tested with [Kotlin Test](https://kotlinlang.org/api/latest/kotlin.test/)

## Usage
KMP-NanoId provides one easy-to-use utility class (`NanoIdUtils`) with multiple methods to generate IDs.

#### Standard IDs - `randomNanoId()`
The default method creates secure, url-friendly, unique ids.
It uses a url-friendly alphabet (`A-Za-z0-9_-`), a secure random number generator, and generates a unique ID wit 21 characters.

##### Java:
```java
String id = NanoIdUtils.randomNanoId();
```

##### Kotlin:
```kotlin
val id = NanoIdUtils.randomNanoId()
```

#### Custom IDs - `NanoIdUtils.randomNanoId(random, alphabet, size)`
Additional methods allow you to generate custom IDs by specifying your own random number generator, alphabet or size.

##### Java:
Methods created by `@JvmOverloads`
```java
Random random = new Random();
char[] alphabet = {'a', 'b', 'c'};
int size = 10;

// Only change random number generator
String idRandom = NanoIdUtils.randomNanoId(random);

// Change random number generator and alphabet
String idRandomAlphabet = NanoIdUtils.randomNanoId(random, alphabet);

// Change all parameter
String idRandomAlphabetSize = NanoIdUtils.randomNanoId(random, alphabet, size);
``` 

#### Kotlin:
```kotlin
val kRandom = Random()
val kAlphabet = charArrayOf('a', 'b', 'c')
val kSize = 10

// Only change random number generator
val idRandom = NanoIdUtils.randomNanoId(kRandom)

// Change random number generator and alphabet
val idRandomAlphabet = NanoIdUtils.randomNanoId(kRandom, kAlphabet)

// Change all parameter
val idRandomAlphabetSize = NanoIdUtils.randomNanoId(kRandom, kAlphabet, kSize)

// Change alphabet only (keep default random number generator and size)
val idAlphabet = NanoIdUtils.randomNanoId(alphabet = kAlphabet)

// Change size only (keep default random number generator and alphabet)
val idSize = NanoIdUtils.randomNanoId(size = kSize)

// Change alphabet and size (keep default random number generator)
val idAlphabetSize = NanoIdUtils.randomNanoId(alphabet = kAlphabet, size = kSize)
```

Needed in Java (can be used in Kotlin)

##### Default values:
```java
// Useful to keep some default values and change only specific
NanoIdUtils.DEFAULT_NUMBER_GENERATOR
NanoIdUtils.DEFAULT_ALPHABET
NanoIdUtils.DEFAULT_SIZE

// Usage example
String idAlphabet = NanoIdUtils.randomNanoId(Random(), NanoIdUtils.DEFAULT_ALPHABET, 10);
```

## License
Code released under the [MIT License]()

## Other Programming Languages

* [C#](https://github.com/codeyu/nanoid-net)
* [Clojure and ClojureScript](https://github.com/zelark/nano-id)
* [Crystal](https://github.com/mamantoha/nanoid.cr)
* [Dart](https://github.com/pd4d10/nanoid)
* [Go](https://github.com/matoous/go-nanoid)
* [Elixir](https://github.com/railsmechanic/nanoid)
* [Haskell](https://github.com/4e6/nanoid-hs)
* [Java](https://github.com/aventrix/jnanoid)
* [JavaScript](https://github.com/ai/nanoid)
* [Nim](https://github.com/icyphox/nanoid.nim)
* [PHP](https://github.com/hidehalo/nanoid-php)
* [Python](https://github.com/puyuan/py-nanoid)
* [Ruby](https://github.com/radeno/nanoid.rb)
* [Rust](https://github.com/nikolay-govorov/nanoid)
* [Swift](https://github.com/antiflasher/NanoID)

Also, a [CLI tool](https://github.com/twhitbeck/nanoid-cli) is available to generate IDs from the command line.
