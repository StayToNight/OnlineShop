package com.staytonight.domain.model

import kz.arcana.qrmenu.domain.model.SectionMarker

data class LatestDealSection(
    val titleStringId: Int,
    val items: List<Latest>
) : SectionMarker
