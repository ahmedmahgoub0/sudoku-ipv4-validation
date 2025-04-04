package ipv4_checker

fun main(){

    // region input validation
    test(
        name = "Given a null input, when validating, then it should return false",
        result = checkValidIPv4Address(null),
        correctResult = false
    )

    test(
        name = "Given an empty string, when validating, then it should return false",
        result = checkValidIPv4Address(""),
        correctResult = false
    )

    test(
        name = "Given a string with segments equal to zero, when validating, then it should return true",
        result = checkValidIPv4Address("192.168.0.1"),
        correctResult = true
    )

    test(
        name = "Given a valid string, when validating, then it should return true",
        result = checkValidIPv4Address("192.168.1.1"),
        correctResult = true
    )
    // endregion

    // region segments
    test(
        name = "Given a string with empty segments, when validating, then it should return false",
        result = checkValidIPv4Address("192..5.1"),
        correctResult = false
    )

    test(
        name = "Given a string with less segments, when validating, then it should return false",
        result = checkValidIPv4Address("192.168.1"),
        correctResult = false
    )
    // endregion

    // region wrong inputs
    test(
        name = "Given a string with special characters, when validating, then it should return false",
        result = checkValidIPv4Address("192.1-8.33.1"),
        correctResult = false
    )

    test(
        name = "Given a string with numbers more than 255, when validating, then it should return false",
        result = checkValidIPv4Address("166.5.256.1"),
        correctResult = false
    )

    test(
        name = "Given a string with leading zeros segments, when validating, then it should return false",
        result = checkValidIPv4Address("001.59.07.2"),
        correctResult = false
    )
    // endregion
}

fun test(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("✅ Success - $name")
    } else {
        println("❌ Failed - $name")
    }
}