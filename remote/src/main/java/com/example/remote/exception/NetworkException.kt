package com.example.remote.exception

import okio.IOException

class NetworkException(message: String? = null) : IOException(message)