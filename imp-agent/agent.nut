// Log the URLs we need
server.log("Turn LED On: " + http.agenturl() + "?led=1");
server.log("Turn LED Off: " + http.agenturl() + "?led=0");


local onState = false // Assume device is off at first TODO: is it safe to assume device state a priori?
function requestHandler(request, response) {
    try {

        // Toggles power to the device
        if ("powerToggle" in request.query) {

            onState != onState // Flip state

            local command = {
                powerState = onState ? 1 : 0
            };

            // send flipped power state to device
            device.send("command", command);
        }

        // Sets the device power to either on (1) or off (0)
        if ("setPower" in request.query) {
            if (request.query.setPower == "1" || request.query.setPower == "0") {

                local command = {
                    powerState = request.query.setPower.tointeger()
                };

                device.send("setPower", command);
            }
        }

        // Reads in a dim command, whose value ranges from 0 to 65535
        if ("dim" in request.query) {
            local command = {
                dimValue = request.query.dim.tointeger()
            };

            // send "led" message to device, and send ledState as the data
            device.send("setDim", dimValue);
        }
        // send a response back saying everything was OK.
        response.send(200, "OK");
    } catch (ex) {
        response.send(500, "Internal Server Error: " + ex);
    }
}

// register the HTTP handler
http.onrequest(requestHandler);
