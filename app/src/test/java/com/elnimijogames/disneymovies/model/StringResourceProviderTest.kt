package com.elnimijogames.disneymovies.model

import com.elnimijogames.disneymovies.R
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class StringResourceProviderTest {

    @Test
    fun testGetString() {
        val mockStringResourceProvider = mockk<StringResourceProvider>()
        val resourceId = R.string.login_go
        val parameter = ""

        every { mockStringResourceProvider.getString(resourceId, parameter) } returns "Go"

        val result = mockStringResourceProvider.getString(resourceId, parameter)

        assertEquals("Go", result)
    }
}