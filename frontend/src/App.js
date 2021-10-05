import './App.css';
import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import ServiceRequestList from "./components/ServiceRequestList";

function App() {
    return (
        <div className="App">
            <AppBar position="static" color="default">
                <Toolbar>
                    <Typography variant="h6" color="inherit">
                        List service request
                    </Typography>
                </Toolbar>
            </AppBar>
            <ServiceRequestList/>
        </div>
    );
}

export default App;
