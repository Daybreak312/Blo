package com.example.blo.env

object BlogTestEnv {
    const val NAME = "이름"
    const val NAME_UPDATE = "이름 수정"

    const val INTRODUCTION = "소개"
    const val INTRODUCTION_UPDATE = "소개 수정"

    val TAGS = listOf("태그1", "태그2")
    val TAGS_UPDATE_ADD = listOf("태그3")
    val TAGS_UPDATE_REMOVE = listOf("태그1")
    val TAGS_UPDATE_RESULT = listOf("태그2", "태그3")
}