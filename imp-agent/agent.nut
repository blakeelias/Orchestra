// Log the URLs we need
server.log("Turn LED On: " + http.agenturl() + "?led=1");
server.log("Turn LED Off: " + http.agenturl() + "?led=0");


local onState = false // Assume device is off at first TODO: is it safe to assume device state a priori?
function requestHandler(request, response) {
    try {


        // Toggles power to the device
        if ("powerToggle" in request.query) {

            onState != onState // Flip state

            // send flipped power state to device
            device.send("setPowerState", onState);
        }

        // Sets the device power to either on (1) or off (0)
        if ("setPowerState" in request.query) {

            // if they did, and led=1.. set our variable to 1
            if (request.query.led == "1" || request.query.led == "0") {
                // convert the led query parameter to an integer
                local ledState = request.query.led.tointeger();

                // send "led" message to device, and send ledState as the data
                device.send("led", ledState); 
            }
        }
        if ("led" in request.query) {

            // if they did, and led=1.. set our variable to 1
            if (request.query.led == "1" || request.query.led == "0") {
                // convert the led query parameter to an integer
                local ledState = request.query.led.tointeger();

                // send "led" message to device, and send ledState as the data
                device.send("led", ledState); 
            }
        }
        // send a response back saying everything was OK.
        response.send(200, "OK");
    } catch (ex) {
        response.send(500, "Internal Server Error: " + ex);
    }
    }

    // register the HTTP handler
    http.onrequest(requestHandler):q;
