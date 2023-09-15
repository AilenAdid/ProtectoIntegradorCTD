import React from "react";
import Header from "../components/Header/Header";
import Footer from "../components/Footer/Footer";
import {Wrapper} from "./MainLayout.styles"
import { useState, createContext } from "react";

const MainLayout =({children})=>{
    const [endpoint, setEndpoint] = useState('http://localhost:8081/products');
    return(
        <Wrapper>
            <EndpointContext.Provider value={[endpoint, setEndpoint]}>
            <Header/>
            {children}
            <Footer/>
            </EndpointContext.Provider>
        </Wrapper>
    )
}
export const EndpointContext = createContext();
export default MainLayout