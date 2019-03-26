package com.edc.flutter.plugin.aliyun.log

data class InitParam(val endPoint: String?,
                     val accessKeyID: String?,
                     val accessKeySecret: String?,
                     val token: String?,
                     val projectName: String?
) {

    companion object {
        fun fromMap(map: Map<String, String>): InitParam {
            return InitParam(
                    map["endPoint"],
                    map["accessKeyID"],
                    map["accessKeySecret"],
                    map["token"],
                    map["projectName"]
            )
        }
    }

    fun toMap(): Map<String, String?> = mapOf(
            "endPoint" to endPoint,
            "accessKeyId" to accessKeyID,
            "accessKeySecret" to accessKeySecret,
            "token" to token,
            "projectName" to projectName
    )
}
