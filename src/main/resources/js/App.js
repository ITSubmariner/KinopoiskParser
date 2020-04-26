import React, { Component} from "react";
import Title from "./components/Title";
import Parser from "./pages/Parser";
import "../css/style.css"

class App extends Component{
    render(){
        return(
            <div className="App">
                <Title />
                <Parser />
            </div>
    );
    }
}

export default App;