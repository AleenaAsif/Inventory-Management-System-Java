import com.example.inventorymanagementsystem.DatabaseConnectionManager;
import com.example.inventorymanagementsystem.Services.InventoryService;
import com.example.inventorymanagementsystem.Domains.InventoryDomain;
import com.example.inventorymanagementsystem.Domains.ItemCategoryDomain;
import com.example.inventorymanagementsystem.Domains.ItemLocationDomain;
import com.example.inventorymanagementsystem.Services.ItemCategoryService;
import com.example.inventorymanagementsystem.Services.ItemLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



    class InventoryServiceTest {

        private InventoryService inventoryService = new InventoryService();

        @Test
        void fetchAllInventoriesTest() {
        List<InventoryDomain> expectedInventories = new ArrayList<>();
        expectedInventories.add(new InventoryDomain(1, "reverse", 20, new ItemLocationDomain(1, "Phoenix"), new ItemCategoryDomain(1, "Laptop")));
        expectedInventories.add(new InventoryDomain(2, "iPhone 13 Pro Max", 5, new ItemLocationDomain(1, "Phoenix"), new ItemCategoryDomain(2, "Phone")));
        expectedInventories.add(new InventoryDomain(3, "Macbook Air", 15, new ItemLocationDomain(2, "Arizona"), new ItemCategoryDomain(1, "Laptop")));
        expectedInventories.add(new InventoryDomain(4, "Macbook Pro", 20, new ItemLocationDomain(2, "Arizona"), new ItemCategoryDomain(1, "Laptop")));

        List<InventoryDomain> actualInventories = inventoryService.fetchAllInventories();

        assertEquals(expectedInventories, actualInventories);
    }

        @Test
        void fetchInventoryById_returnsInventoryWithCorrectId() {

            int inventoryId = 1;


            InventoryDomain inventory = inventoryService.fetchInventoryById(inventoryId);


            assertEquals(inventoryId, inventory.getId());
        }

        @Test
        void fetchInventoryById_returnsNullWhenInventoryNotFound() {

            int inventoryId = 999;


            InventoryDomain inventory = inventoryService.fetchInventoryById(inventoryId);


            assertEquals(null, inventory);
        }

        @Test
        void fetchInventoryByCategoryTest() {

            List<InventoryDomain> expectedInventories = new ArrayList<>();

            expectedInventories.add(new InventoryDomain(2, "iPhone 13 Pro Max", 5, new ItemLocationDomain(1, "Phoenix"), new ItemCategoryDomain(2, "Phone")));

            assertEquals(expectedInventories, inventoryService.fetchInventoriesByCategory(24));
        }


        @Test
        void fetchInventoryByLocationTest() {

            List<InventoryDomain> expectedInventories = new ArrayList<>();

            expectedInventories.add(new InventoryDomain(2, "iPhone 13 Pro Max", 5, new ItemLocationDomain(1, "Phoenix"), new ItemCategoryDomain(2, "Phone")));


           int location = 7;


            List<InventoryDomain> actualInventories = inventoryService.fetchInventoriesByLocation(location);


            assertEquals(expectedInventories, actualInventories);
        }
    }



