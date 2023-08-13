package com.example.inventorymanagementsystem.Resources;

import com.example.inventorymanagementsystem.Domains.InventoryDomain;
import com.example.inventorymanagementsystem.Services.InventoryService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {
    private static final Logger logger = LoggerFactory.getLogger("LogFile");


    public final InventoryService inventoryService = new InventoryService();

    @GET
    @Path("/list")
    //fetching all inventories from database
    public List<InventoryDomain> fetchAllInventories() {
        logger.info("here");
        return inventoryService.fetchAllInventories();

    }

    @GET
    @Path("/{inventory_id}")
//fetching an inventory from database based on id provided by the user
    public Response fetchInventoryById(@PathParam("inventory_id") int inventoryId) {
        InventoryDomain inventoryDomain = inventoryService.fetchInventoryById(inventoryId);
        if (inventoryDomain != null) {
            logger.info("Fetched inventory by id");
            return Response.ok(inventoryDomain).build();
        } else {
            logger.error("Could not fetch inventory by id");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/listByCategory")
    public List<InventoryDomain> fetchInventoriesByCategory(@QueryParam("category") int categoryId) {
        logger.info("Fetching inventories by category");
        return inventoryService.fetchInventoriesByCategory(categoryId);
    }

    @GET
    @Path("/listByLocation")
    public List<InventoryDomain> fetchInventoriesByLocation(@QueryParam("location") int locationId) {
        logger.info("Fetching inventories by location");
        return inventoryService.fetchInventoriesByLocation(locationId);
    }

    @GET
    @Path("/listByLocationAndCategory")
    public List<InventoryDomain> fetchInventoriesByLocationAndCategory(
            @QueryParam("location") int locationId,
            @QueryParam("category") int categoryId) {
        logger.info("Fetching inventories by location and category");
        return inventoryService.fetchInventoriesByLocationAndCategory(locationId, categoryId);
    }

    @POST
    @Path("/add")
    public Response addNewInventoryItem(InventoryDomain inventoryDomain) {
        Response newInventoryResponse = inventoryService.addNewInventoryItem(inventoryDomain);

        if (newInventoryResponse.getStatus() == Response.Status.CREATED.getStatusCode()) {
            InventoryDomain addedInventory = (InventoryDomain) newInventoryResponse.getEntity();
            logger.info("Adding new inventory item");
            return Response.status(Response.Status.CREATED).entity(addedInventory).build();
        } else {
            logger.error("Failed to add new inventory item");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{inventory_id}")
    public Response updateInventory(@PathParam("inventory_id") int inventoryId, InventoryDomain inventoryDomain) {
        logger.info("Updating inventory");
        Response updatedInventoryResponse = inventoryService.updateInventory(inventoryId, inventoryDomain);

        if (updatedInventoryResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            logger.info("Inventory updated");
            return Response.ok(updatedInventoryResponse.getEntity()).build();
        } else if (updatedInventoryResponse.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            logger.error("Inventory update response not found");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            logger.error("Failed to update inventory");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DELETE
    @Path("/{inventory_id}")
    public Response deleteInventory(@PathParam("inventory_id") int inventoryId) {
        boolean deleted = inventoryService.deleteInventory(inventoryId);
        logger.info("Deleting inventory");
        if (deleted) {
            logger.info("Inventory deleted");
            return Response.ok().entity("Inventory item deleted.").build();
        } else {
            logger.error("Inventory could not be deleted");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
