package com.elnimijogames.disneymovies.model

import android.content.res.Resources
import com.elnimijogames.disneymovies.R
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StringResourceProviderImplTest {

    private val mockResources = mockk<Resources>()

    @Before
    fun setup() {
        every { mockResources.getString(any(), any()) } returns "Go"
    }

    @Test
    fun testGetString() {
        val stringResourceProvider = StringResourceProviderImpl(mockResources)

        val resourceId = R.string.login_go
        val parameter = ""

        val result = stringResourceProvider.getString(resourceId, parameter)

        assertEquals("Go", result)
    }
}