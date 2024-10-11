package com.hvk.jetpackcomposecanvasdemos

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object HomeGraph : Destination

    @Serializable
    data object HomeScreen : Destination
    @Serializable
    data object BottleScreen : Destination
}