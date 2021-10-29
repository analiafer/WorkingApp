package com.example.workingapp.data

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class RoomRepositoryTest {

    private lateinit var instance : RoomRepository

    @MockK
    lateinit var dao : TicketDao

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)


    @ExperimentalCoroutinesApi
    @Test
    fun roomRepoTest(){
        runBlockingTest {

            instance = RoomRepository(dao)
            coEvery {
                dao.getAll()
            } returns  listOf(
                    TicketEntity(1,"Ticket de prueba","Ana","Este es un ticket de prueba", "29/10/2021","Realizado"))

            val resultado = instance.getAll()
            val sizeList = resultado.size
            assertEquals(1, sizeList)
        }
    }
 }