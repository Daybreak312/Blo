package com.example.blo.env

object TagTestEnv {

    private const val tag1 = "태그1"
    private const val tag2 = "태그2"
    private const val tag3 = "태그3"

    val TAGS = listOf(tag1, tag2)
        get() = copy(field)

    val TAGS_UPDATE_ADD = listOf(tag3)
        get() = copy(field)

    val TAGS_UPDATE_REMOVE = listOf(tag1)
        get() = copy(field)

    val TAGS_UPDATE_RESULT = listOf(tag2, tag3)
        get() = copy(field)

    val TAGS_ALL = listOf(tag1, tag2, tag3)

    private fun copy(strings: List<String>): List<String> {
        val copiedStrings = listOf<String>()
        strings.map { copiedStrings.plus(it) }
        return copiedStrings
    }
}