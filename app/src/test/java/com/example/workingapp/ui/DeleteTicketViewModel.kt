package com.example.workingapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.workingapp.CoroutineTestRule
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.data.TicketsRepository
import com.example.workingapp.model.Ticket
import com.example.workingapp.ui.viewModel.NewTicketViewModel
import com.example.workingapp.ui.viewModel.ViewTicketViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeleteTicketViewModel {

    lateinit var instance: ViewTicketViewModel

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
            instance = ViewTicketViewModel(repo)
            val ticket = TicketEntity(1,"Ticket de prueba", "Analia", "Este es un ticket de prueba","30/10/2021", "cancelado")
            instance.delete(ticket)
            coVerify(exactly = 1) { repo.save(any()) }

        }
    }


}