package com.example.onlinestore.Data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.serializer.JacksonSerializer
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage

object SupabaseClient {

    var clientDatabase = createSupabaseClient(
        supabaseKey = BuildConfig.SUPABASE_KEY,
        supabaseUrl = BuildConfig.SUPABASE_URL
    ){
        defaultSerializer = JacksonSerializer()
        install(Storage)
        install(Postgrest)
        install(Realtime)
    }
}