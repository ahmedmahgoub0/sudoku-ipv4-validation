package ipv4_checker

fun main() {
    println(checkValidIPv4Address("001.59.07.2"))
}

fun checkValidIPv4Address(input: String?): Boolean {
    if (input.isNullOrEmpty()) return false

    val segments = input.split('.')
    if (segments.size != 4) return false

    segments.forEach { segment ->
        if (segment.isEmpty() || segment.isBlank()) return false

        if (segment.any { it.isLetter() || !it.isDigit()}) return false

        val segmentValue = segment.toIntOrNull() ?: return false
        if(segmentValue !in 0..255) return false

        if(segment != segment.toInt().toString()) return false
    }

    return true
}