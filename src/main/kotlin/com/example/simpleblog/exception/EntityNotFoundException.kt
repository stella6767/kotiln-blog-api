package com.example.simpleblog.exception

sealed class EntityNotFoundException(message:String?) : BusinessException(message, ErrorCode.ENTITY_NOT_FOUND)

class MemberNotFoundException(id:String) : EntityNotFoundException("$id not found")