package com.edc.flutter.plugin.aliyun.log

data class KeyContent(val key: String, val content: String) {
    companion object {
        fun fromMap(map: Map<String, String>): KeyContent {
            return KeyContent(
                    map["key"] as String,
                    map["content"] as String
            )
        }

        fun fromListMap(listMap: List<Map<String, String>>): List<KeyContent> {
            return listMap.map { fromMap(it) }
        }
    }

    fun toMap(): Map<String, String> = mapOf(
            "key" to key,
            "content" to content
    )
}

data class PostParam(val logStore: String, val topic: String, val source: String, val keyContents: List<KeyContent>) {

    companion object {
        fun fromMap(map: Map<String, Any>): PostParam {
            return PostParam(
                    map["store"] as String,
                    map["topic"] as String,
                    map["source"] as String,
                    KeyContent.fromListMap(map["keyContents"] as List<Map<String, String>>)
            )
        }
    }

    fun toMap(): Map<String, Any?> = mapOf(
            "store" to logStore,
            "topic" to topic,
            "source" to source,
            "keyContents" to keyContents.map { it.toMap() }
    )
}
