package com.staytonight.domain.model

import kz.arcana.qrmenu.domain.model.SectionMarker

data class CategorySection(
    val items: List<Category>
) : SectionMarker
