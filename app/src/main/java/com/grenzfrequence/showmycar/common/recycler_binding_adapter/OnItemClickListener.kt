package com.grenzfrequence.showmycar.common.recycler_binding_adapter

/**
 * Created by grenzfrequence on 19/03/17.
 */

interface OnItemClickListener<ITEM> {
    fun onItemClicked(item: ITEM, position: Int)
}
