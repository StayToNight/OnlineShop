package com.staytonight.domain.model

import kz.arcana.qrmenu.domain.model.SectionMarker

data class FlashSaleSection(
    val titleStringId: Int,
    val items: List<FlashSale>
) : SectionMarker
