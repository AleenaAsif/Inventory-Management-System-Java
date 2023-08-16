package com.example.inventorymanagementsystem.Resources;

import com.example.inventorymanagementsystem.Domains.InventoryDomain;
import com.example.inventorymanagementsystem.Services.AuthenticationService;
import com.example.inventorymanagementsystem.Services.InventoryService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {
    private static final Logger logger = LoggerFactory.getLogger("LogFile");


    public final InventoryService inventoryService = new InventoryService();
    public final AuthenticationService authService = new AuthenticationService();


    public boolean isAuthorized(String username, String password, String[] allowedRoles) {
        String userRole = authService.getUserRole(username, password);
        if (userRole != null) {
            for (String role : allowedRoles) {
                if (role.equalsIgnoreCase(userRole)) {
                    logger.info("Authorization received in InventoryResource");
                    return true;
                }
            }
        }
        logger.error("Authorization not received in InventoryResource");
        return false;
    }


    @GET
    @Path("/list")
    //fetching all inventories from database
    public List<InventoryDomain> fetchAllInventories(@HeaderParam("Authorization") String basicAuth) {
        logger.info("GET request received");
        String[] usernamePassword = getUsernameAndPasswordFromBasicAuth(basicAuth);

        if (usernamePassword != null && usernamePassword.length == 2) {
            String username = usernamePassword[0];
            String password = usernamePassword[1];

            String[] allowedRoles = {"admin", "user"};
            if (isAuthorized(username, password, allowedRoles)) {
                logger.info("Authorized :InventoryResource");
                return inventoryService.fetchAllInventories();
            } else {
                logger.error("Unauthorized access : InventoryResource");
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            logger.error("Invalid authorization credentials  : InventoryResource");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }


    @GET
    @Path("/{inventory_id}")
    public Response fetchInventoryById(@PathParam("inventory_id") int inventoryId,
                                       @HeaderParam("Authorization") String basicAuth) {
        logger.info("GET request received");
        String[] usernamePassword = getUsernameAndPasswordFromBasicAuth(basicAuth);

        if (usernamePassword != null && usernamePassword.length == 2) {
            String username = usernamePassword[0];
            String password = usernamePassword[1];

            String[] allowedRoles = {"admin", "user"};
            if (isAuthorized(username, password, allowedRoles)) {
                InventoryDomain inventoryDomain = inventoryService.fetchInventoryById(inventoryId);
                if (inventoryDomain != null) {
                    logger.info("Fetched inventory by id  : InventoryResource");
                    return Response.ok(inventoryDomain).build();
                } else {
                    logger.error("Could not fetch inventory by id  : InventoryResource");
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            } else {
                logger.error("Unauthorized access  : InventoryResource");
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            logger.error("Invalid authorization credentials  : InventoryResource");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }


    @GET
    @Path("/listByCategory")
    public List<InventoryDomain> fetchInventoriesByCategory(@QueryParam("category") int categoryId,
                                                            @HeaderParam("Authorization") String basicAuth) {
        logger.info("GET request received");
        logger.info("reached ListByCategory: InventoryResource");
        String[] usernamePassword = getUsernameAndPasswordFromBasicAuth(basicAuth);

        if (usernamePassword != null && usernamePassword.length == 2) {
            String username = usernamePassword[0];
            String password = usernamePassword[1];

            String[] allowedRoles = {"admin", "user"};
            if (isAuthorized(username, password, allowedRoles)) {
                logger.info("Fetching inventories by category : InventoryResource");
                return inventoryService.fetchInventoriesByCategory(categoryId);
            } else {
                logger.error("Unauthorized access : InventoryResource");
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            logger.error("Invalid authorization credentials : InventoryResource");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }


    @GET
    @Path("/listByLocation")
    public List<InventoryDomain> fetchInventoriesByLocation(@QueryParam("location") int locationId,
                                                            @HeaderParam("Authorization") String basicAuth) {
        logger.info("GET request receivedd");
        String[] usernamePassword = getUsernameAndPasswordFromBasicAuth(basicAuth);

        if (usernamePassword != null && usernamePassword.length == 2) {
            String username = usernamePassword[0];
            String password = usernamePassword[1];

            String[] allowedRoles = {"admin", "user"};
            if (isAuthorized(username, password, allowedRoles)) {
                logger.info("Fetching inventories by location  : InventoryResource");
                return inventoryService.fetchInventoriesByLocation(locationId);
            } else {
                logger.error("Unauthorized access : InventoryResource");
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            logger.error("Invalid authorization credentials : InventoryResource");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }


    @GET
    @Path("/listByLocationAndCategory")
    public List<InventoryDomain> fetchInventoriesByLocationAndCategory(
            @QueryParam("location") int locationId,
            @QueryParam("category") int categoryId,
            @HeaderParam("Authorization") String basicAuth) {
        logger.info("GET request received");
        String[] usernamePassword = getUsernameAndPasswordFromBasicAuth(basicAuth);

        if (usernamePassword != null && usernamePassword.length == 2) {
            String username = usernamePassword[0];
            String password = usernamePassword[1];

            String[] allowedRoles = {"admin", "user"};
            if (isAuthorized(username, password, allowedRoles)) {
                logger.info("Fetching inventories by location and category : InventoryResource");
                return inventoryService.fetchInventoriesByLocationAndCategory(locationId, categoryId);
            } else {
                logger.error("Unauthorized access : InventoryResource");
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            logger.error("Invalid authorization credential : InventoryResources");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }


    @POST
    @Path("/add")
    public Response addNewInventoryItem(String payload, @HeaderParam("Authorization") String basicAuth) {
        logger.info("POST request received");

        String[] usernamePassword = getUsernameAndPasswordFromBasicAuth(basicAuth);

        if (usernamePassword != null && usernamePassword.length == 2) {
            String username = usernamePassword[0];
            String password = usernamePassword[1];

            String[] allowedRoles = {"admin"};
            if (isAuthorized(username, password, allowedRoles)) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    InventoryDomain inventoryDomain = objectMapper.readValue(payload, InventoryDomain.class);

                    InventoryDomain addedInventory = inventoryService.addNewInventoryItem(inventoryDomain);

                    if (addedInventory != null) {
                        logger.info("Adding new inventory item : InventoryResource");
                        return Response.status(Response.Status.CREATED).entity(addedInventory).build();
                    } else {
                        logger.error("Failed to add new inventory item : InventoryResource");
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                    }
                } catch (Exception e) {
                    logger.error("Error processing JSON payload: " + e.getMessage());
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
            } else {
                logger.error("Unauthorized access : InventoryResource");
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            logger.error("Invalid authorization credentials : InventoryResource");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }

    @PUT
    @Path("/{inventory_id}")
    public Response updateInventory(@PathParam("inventory_id") int inventoryId, String payload,
                                    @HeaderParam("Authorization") String basicAuth) {
        logger.info("PUT request received");
        String[] usernamePassword = getUsernameAndPasswordFromBasicAuth(basicAuth);

        if (usernamePassword != null && usernamePassword.length == 2) {
            String username = usernamePassword[0];
            String password = usernamePassword[1];

            String[] allowedRoles = {"admin"};
            if (isAuthorized(username, password, allowedRoles)) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    InventoryDomain inventoryDomain = objectMapper.readValue(payload, InventoryDomain.class);

                    InventoryDomain updatedInventory = inventoryService.updateInventory(inventoryId, inventoryDomain);

                    if (updatedInventory != null) {
                        logger.info("Updating inventory : InventoryResource");
                        return Response.ok(updatedInventory).build();
                    } else {
                        logger.error("Failed to update inventory : InventoryResource");
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                    }
                } catch (Exception e) {
                    logger.error("Error processing JSON payload: " + e.getMessage());
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
            } else {
                logger.error("Unauthorized access : InventoryResource");
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            logger.error("Invalid authorization credentials : InventoryResource");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }


    @DELETE
    @Path("/{inventory_id}")
    public Response deleteInventory(@PathParam("inventory_id") int inventoryId,
                                    @HeaderParam("Authorization") String basicAuth) {
        logger.info("DELETE request received");
        String[] usernamePassword = getUsernameAndPasswordFromBasicAuth(basicAuth);

        if (usernamePassword != null && usernamePassword.length == 2) {
            String username = usernamePassword[0];
            String password = usernamePassword[1];

            String[] allowedRoles = {"admin"};
            if (isAuthorized(username, password, allowedRoles)) {
                boolean deleted = inventoryService.deleteInventory(inventoryId);
                logger.info("Deleting inventory : InventoryResource");
                if (deleted) {
                    logger.info("Inventory deleted : InventoryResource");
                    return Response.ok().entity("Inventory item deleted. : InventoryResource").build();
                } else {
                    logger.error("Inventory could not be deleted : InventoryResource");
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            } else {
                logger.error("Unauthorized access : InventoryResource");
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            logger.error("Invalid authorization credentials : InventoryResource");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }

    private String[] getUsernameAndPasswordFromBasicAuth(String basicAuth) {
        String[] credentials = basicAuth.split(" ");

        if (credentials.length == 2 && "Basic".equalsIgnoreCase(credentials[0])) {
            String decodedCredentials = new String(Base64.getDecoder().decode(credentials[1]));
            String[] usernamePassword = decodedCredentials.split(":");
            logger.info("got username and password from basic auth : InventoryResource");

            if (usernamePassword.length == 2) {
                return usernamePassword;
            }
        }
        logger.info("could not get username and password from basic auth : InventoryResource");


        return null;
    }

}