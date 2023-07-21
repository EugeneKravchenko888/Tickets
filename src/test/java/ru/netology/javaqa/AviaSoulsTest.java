package ru.netology.javaqa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.manager.AviaSouls;

import java.util.Comparator;

public class AviaSoulsTest {
    Ticket ticket1 = new Ticket("City1", "City2", 30_000, 15, 19);
    Ticket ticket2 = new Ticket("City3", "City1", 31_000, 23, 1);
    Ticket ticket3 = new Ticket("City1", "City2", 5_000, 11, 13);
    Ticket ticket4 = new Ticket("City4", "City1", 30_000, 2, 6);
    Ticket ticket5 = new Ticket("City3", "City1", 10_000, 15, 17);
    Ticket ticket6 = new Ticket("City1", "City2", 22_000, 5, 11);
    Ticket ticket7 = new Ticket("City1", "City2", 50_000, 23, 5);
    AviaSouls manager = new AviaSouls();

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
    }

    @Test
    public void testCompareToReturnsOneForHigherPriceTicket() {
        System.out.println(ticket1.compareTo(ticket5));

        int expected = 1;
        int actual = ticket1.compareTo(ticket5);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testCompareToReturnsMinusOneForLowerPriceTicket() {
        System.out.println(ticket1.compareTo(ticket2));

        int expected = -1;
        int actual = ticket1.compareTo(ticket2);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testCompareToReturnsZeroForEqualPriceTickets() {
        System.out.println(ticket1.compareTo(ticket4));

        int expected = 0;
        int actual = ticket1.compareTo(ticket4);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testSearchReturnsTicketsSortedByPrice() {
        Ticket[] expected = {ticket3, ticket6, ticket1, ticket7};
        Ticket[] actual = manager.search("City1", "City2");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchReturnsSingleMatchingTicket() {
        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.search("City4", "City1");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchReturnsEmptyArrayWhenNoMatchingTicketsFound() {
        Ticket[] expected = {};
        Ticket[] actual = manager.search("City3", "City5");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByReturnsTicketsSortedByFlightTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket3, ticket1, ticket6, ticket7};
        Ticket[] actual = manager.searchAndSortBy("City1", "City2", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

}
