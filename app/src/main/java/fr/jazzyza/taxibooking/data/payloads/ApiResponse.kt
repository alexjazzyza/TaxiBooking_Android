package fr.jazzyza.taxibooking.data.payloads

data class ApiResponse<T, Boolean, E: Exception>(var data: T? = null, var loading: Boolean? = null, var e: E? = null) {
}
