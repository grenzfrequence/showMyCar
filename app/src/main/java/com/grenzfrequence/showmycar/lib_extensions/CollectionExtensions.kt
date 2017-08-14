package com.grenzfrequence.showmycar.lib_extensions

import org.json.JSONObject

/**
 * Created by grenzfrequence on 14.08.17.
 */

fun Map<String, String>.isEqual(jsonObject: JSONObject): Boolean {
    return this == jsonObject.toMap()
}
