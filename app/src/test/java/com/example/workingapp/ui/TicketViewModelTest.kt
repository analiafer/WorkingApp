package com.example.workingapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.workingapp.CoroutineTestRule
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.data.TicketsRepository
import com.example.workingapp.ui.viewModel.NewTicketViewModel
import com.example.workingapp.ui.viewModel.TicketViewModel
import io.mockk.MockKAnnotations
import io.mockk.*
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TicketViewModelTest {
    lateinit var instance: NewTicketViewModel

    @MockK
    lateinit var repo: TicketsRepository

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `agregar un ticket`() {
        coroutineTestRule.testDispatcher.runBlockingTest {

            coJustRun {
                repo.save(any())
            }
            coEvery {
                repo.getAll()
            } returns listOf()
            instance = NewTicketViewModel(repo)
            instance.saveTicket("Ticket de prueba", "Analia", "Este es un ticket de prueba")
            coVerify(exactly = 1) { repo.save(any()) }

        }
    }




}