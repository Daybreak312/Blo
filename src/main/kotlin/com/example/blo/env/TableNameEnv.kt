package com.example.blo.env

object TableNameEnv {
    private const val tablePrefix = "tbl_"
    private const val joinerPrefix = "join_"
    const val accountTable = "${tablePrefix}account"
    const val tagTable = "${tablePrefix}tag"
    const val blogTable = "${tablePrefix}blog"
    const val blogTagJoinerTable = "${joinerPrefix}blog_tag"
}