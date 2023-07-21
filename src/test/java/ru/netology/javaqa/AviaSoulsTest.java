package ru.netology.javaqa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.manager.AviaSouls;

import java.util.Comparator;

public class AviaSoulsTest {
    Ticket ticket1 = new Ticket("City1", "City2", 10_000, 13, 21);
    Ticket ticket2 = new Ticket("City3", "City1", 11_000, 11, 2);
    Ticket ticket3 = new Ticket("City1", "City2", 17_000, 10, 14);
    Ticket ticket4 = new Ticket("City4", "City1", 20_000, 12, 8);
    Ticket ticket5 = new Ticket("City3", "City1", 13_000, 11, 14);
    Ticket ticket6 = new Ticket("City1", "City2", 11_000, 15, 13);
    Ticket ticket7 = new Ticket("City1", "City2", 18_000, 21, 9);
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
