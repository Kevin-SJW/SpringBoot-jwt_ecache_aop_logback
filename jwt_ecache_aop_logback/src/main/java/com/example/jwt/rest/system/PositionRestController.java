package com.example.jwt.rest.system;


import com.example.jwt.domain.system.Position;
import com.example.jwt.service.PositionService;
import com.example.jwt.utils.HeaderUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;


@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "PositionRestController", description = "position API")
public class PositionRestController {
    @Autowired
    private PositionService positionService;

    @ApiOperation(value = "create position", notes = "create postition",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="position", paramType = "body",value = " {\n" +
                    "{\n" +
                    "    \"id\": 4,\n" +
                    "    \"postName\": \"董事长\",\n" +
                    "    \"postKey\": \"ceo\",\n" +
                    "    \"postSort\": \"1\",\n" +
                    "    \"enabled\": true,\n" +
                    "    \"isDeleted\": \"0\"\n" +
                    "}"
                    ,examples = @Example(value = {@ExampleProperty(value = " ", mediaType = "application/json")})),
//            @ApiImplicitParam(name = "postName", value = "position name", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "postKeyName", value = "position key", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "postSort", value = "position sort", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "enabled", value = "enabled", required = true, dataType = "boolean", paramType = "body"),
//            @ApiImplicitParam(name = "idDeleted", value = "status", required = true, dataType = "String", paramType = "body"),

    })
    @ApiResponses(value = {

            @ApiResponse(code = 201, message = "the ResponseEntity with status 201 (Created) and with body the new dept " +
                    "{\n" +
                    "    \"id\": 4,\n" +
                    "    \"postName\": \"董事长\",\n" +
                    "    \"postKey\": \"ceo\",\n" +
                    "    \"postSort\": \"1\",\n" +
                    "    \"enabled\": true,\n" +
                    "    \"isDeleted\": \"0\"\n" +
                    "}"),
            @ApiResponse(code = 400, message = "with status 400 (Bad Request) if the dept has already an ID"),

    })
    @PostMapping("/posts")
    public ResponseEntity<Position> createPosition(@Valid @RequestBody Position position) throws URISyntaxException {
        log.debug("REST request to save Postition : {}", position);

        Position newPost = positionService.createPost(position);
        return ResponseEntity.created(new URI("/api/posts/" + newPost.getPostName()))
                .headers(HeaderUtil.createAlert("postManagement.created", newPost.getPostName()))
                .body(newPost);
    }

    @ApiOperation(value = "create position", notes = "create postition",httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="position", paramType = "body",value = " {\n" +
                    "{\n" +
                    "    \"id\": 4,\n" +
                    "    \"postName\": \"董事长\",\n" +
                    "    \"postKey\": \"ceo\",\n" +
                    "    \"postSort\": \"0\",\n" +
                    "    \"enabled\": true,\n" +
                    "    \"isDeleted\": \"0\"\n" +
                    "}"
                    ,examples = @Example(value = {@ExampleProperty(value = " ", mediaType = "application/json")})),
//            @ApiImplicitParam(name = "postName", value = "position name", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "postKeyName", value = "position key", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "postSort", value = "position sort", required = true, dataType = "String", paramType = "body"),
//            @ApiImplicitParam(name = "enabled", value = "enabled", required = true, dataType = "boolean", paramType = "body"),
//            @ApiImplicitParam(name = "idDeleted", value = "status", required = true, dataType = "String", paramType = "body"),

    })
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "the ResponseEntity with status 201 (Created) and with body the new dept " +
                    "{\n" +
                    "    \"id\": 4,\n" +
                    "    \"postName\": \"董事长\",\n" +
                    "    \"postKey\": \"ceo\",\n" +
                    "    \"postSort\": \"0\",\n" +
                    "    \"enabled\": true,\n" +
                    "    \"isDeleted\": \"0\"\n" +
                    "}"),
            @ApiResponse(code = 400, message = "with status 400 (Bad Request) if the position is not valid"),
            @ApiResponse(code = 500, message = "with status 500 (Internal Server Error) if the position couldn't be updated"),

    })
    @PutMapping("/posts")
    public ResponseEntity<Position> updatePosition(@Valid @RequestBody Position position) throws URISyntaxException {
        log.debug("REST request to update Position : {}", position);

        Position newposition = positionService.save(position);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, position.getId().toString()))
                .body(newposition);
    }

    @ApiOperation(value = "get all position", notes = "get all position",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the ResponseEntity with status 200 (OK) and the list of position in body" +
                    "{\n" +
                    "        \"id\": 1,\n" +
                    "        \"postName\": \"董事长\",\n" +
                    "        \"postKey\": \"ceo\",\n" +
                    "        \"postSort\": \"1\",\n" +
                    "        \"enabled\": true,\n" +
                    "        \"isDeleted\": \"0\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 2,\n" +
                    "        \"postName\": \"项目经理\",\n" +
                    "        \"postKey\": \"pm\",\n" +
                    "        \"postSort\": \"2\",\n" +
                    "        \"enabled\": true,\n" +
                    "        \"isDeleted\": \"0\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": 3,\n" +
                    "        \"postName\": \"软件开发\",\n" +
                    "        \"postKey\": \"dev\",\n" +
                    "        \"postSort\": \"3\",\n" +
                    "        \"enabled\": true,\n" +
                    "        \"isDeleted\": \"0\"\n" +
                    "    }"),

    })

    @GetMapping("/posts")
    public ResponseEntity<List<Position>> getAllPosition() throws URISyntaxException {
        log.debug("REST request to get All Position : {}");
        List<Position> newPosition = positionService.getAllPositions();
        return new ResponseEntity<>(newPosition, HttpStatus.OK);
    }

    @ApiOperation(value = "get position", notes = "get position",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the ResponseEntity with status 200 (OK) and the single position in body " +
                    "{\n" +
                    "        \"id\": 1,\n" +
                    "        \"postName\": \"董事长\",\n" +
                    "        \"postKey\": \"ceo\",\n" +
                    "        \"postSort\": \"1\",\n" +
                    "        \"enabled\": true,\n" +
                    "        \"isDeleted\": \"0\"\n" +
                    "    }"

            ),

    })
    @GetMapping( "/posts/{id}")
    public ResponseEntity getPosition(@PathVariable Long id) throws URISyntaxException {
        log.info("REST request to get single Position : {}",id);
        Optional<Position> position = positionService.getPosition(id);

        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @ApiOperation(value = "delete position", notes = "delete position",httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the ResponseEntity with status 200 (OK)"),

    })
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        log.debug("REST request to delete Position : {}", id);
        positionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
