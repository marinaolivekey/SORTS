package Manager;

import Domain.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AviaSoulsTest {

    Ticket ticket1 = new Ticket("Moscow","Tokyo", 900, 12, 19);
    Ticket ticket2 = new Ticket("Moscow","Tokyo", 600, 10, 19);
    Ticket ticket3 = new Ticket("Moscow","Tokyo", 400, 18, 19);
    Ticket ticket4 = new Ticket("London","Paris", 700, 8, 19);
    Ticket ticket5 = new Ticket("Moscow","Tokyo", 500, 7, 19);
    Ticket ticket6 = new Ticket("Moscow","Tokyo", 1000, 22, 19);
    Ticket ticket7 = new Ticket("Moscow","Tokyo", 1200, 22, 19);
    Ticket ticket8 = new Ticket("Moscow","Tokyo", 900, 22, 19);

    @Test
    void add() {
        AviaSouls repo = new AviaSouls();
        repo.add(ticket1);
        repo.add(ticket2);
        repo.add(ticket3);
        repo.add(ticket4);
        repo.add(ticket5);
        repo.add(ticket6);

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void findAll() {
        AviaSouls repo = new AviaSouls();
        repo.add(ticket1);

        Ticket[] expected = {ticket1};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchIfCityExist() {
        AviaSouls repo = new AviaSouls();
        repo.add(ticket1);
        repo.add(ticket2);
        repo.add(ticket3);
        repo.add(ticket4);
        repo.add(ticket5);
        repo.add(ticket6);

        Ticket[] expected = {ticket3, ticket5, ticket2, ticket1, ticket6};
        Ticket[] actual = repo.search("Moscow", "Tokyo");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchIfPriceTheSame() {
        AviaSouls repo = new AviaSouls();
        repo.add(ticket1);
        repo.add(ticket8);

        Ticket[] expected = {ticket1, ticket8};
        Ticket[] actual = repo.search("Moscow", "Tokyo");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchIfCityNotExist() {
        AviaSouls repo = new AviaSouls();
        repo.add(ticket1);
        repo.add(ticket2);
        repo.add(ticket3);
        repo.add(ticket4);
        repo.add(ticket5);
        repo.add(ticket6);

        Ticket[] expected = {};
        Ticket[] actual = repo.search("Moscow", "London");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchAndSortBy() {
        AviaSouls repo = new AviaSouls();
        repo.add(ticket1);
        repo.add(ticket2);
        repo.add(ticket3);
        repo.add(ticket4);
        repo.add(ticket5);
        repo.add(ticket6);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket5, ticket2, ticket1, ticket3, ticket6};
        Ticket[] actual = repo.searchAndSortBy("Moscow", "Tokyo", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchAndSortByIfCityNotExist() {
        AviaSouls repo = new AviaSouls();
        repo.add(ticket1);
        repo.add(ticket2);
        repo.add(ticket3);
        repo.add(ticket4);
        repo.add(ticket5);
        repo.add(ticket6);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {};
        Ticket[] actual = repo.searchAndSortBy("Moscow", "London", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchAndSortByIfTimeTheSame() {
        AviaSouls repo = new AviaSouls();
        repo.add(ticket7);
        repo.add(ticket6);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket7, ticket6};
        Ticket[] actual = repo.searchAndSortBy("Moscow", "Tokyo", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}