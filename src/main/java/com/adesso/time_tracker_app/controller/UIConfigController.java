package com.adesso.time_tracker_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/uiconfig")
@CrossOrigin
public class UIConfigController {
    private static final String FIELD = "field";
    private static final String LABEL = "label";
    private static final String HEADER = "header";
    private static final String REQUIRED = "required";
    private static final String TYPE = "type";

        @GetMapping(value = "/{userId}/{lang}")
        public ResponseEntity<Map<String, Object>> getConfig(@PathVariable String userId,
                                                             @PathVariable String lang) {

            Logger logger=Logger.getLogger("UIConfigController.class");
            logger.log(Level.INFO,"inside uiconfig:{0} ", userId);

            Map<String, Object> config = new HashMap<>();

        config.put("columns", List.of(
                Map.of(FIELD, "description", HEADER, "Description"),
                Map.of(FIELD, "logDate", HEADER, "Date"),
                Map.of(FIELD, "hoursLogged", HEADER, "Hours")
        ));

        config.put("dialogFields", List.of(
                Map.of(FIELD, "description", LABEL, "Description", TYPE, "text", REQUIRED, true),
                Map.of(FIELD, "logDate", LABEL, "Date", TYPE, "date", REQUIRED, true),
                Map.of(FIELD, "hoursLogged", LABEL, "Hours", TYPE, "number", REQUIRED, true)
        ));
            logger.log(Level.INFO,"Returning config:{0} ", config);

        return ResponseEntity.ok(config);
    }
}
