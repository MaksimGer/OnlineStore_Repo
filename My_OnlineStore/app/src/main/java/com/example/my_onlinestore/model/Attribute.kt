package com.example.my_onlinestore.model

import com.example.my_onlinestore.model.server_dto.ServerCategory

data class Attribute(val id: Long, val name: String, val categories: Set<ServerCategory>)
