package com.grenzfrequence.showmycar.lib_extensions

import org.json.JSONObject

/**
 * Created by grenzfrequence on 14.08.17.
 */

fun JSONObject.toMap(): Map<String, String> {
    var map: LinkedHashMap<String, String> = LinkedHashMap()
    for (key in this.keys()) {
        map.put(key, this.getString(key))
    }
    return map;
}