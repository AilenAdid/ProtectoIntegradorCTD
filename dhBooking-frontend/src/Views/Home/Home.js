import React from "react";
import {Main} from "./Home.styles"
import Categories from "../../components/Categories/Categories";
import Products from "../../components/Products/Products";
import SearchBar from "../../components/SearchBar/SearchBar";
import {useState, useContext} from "react";
import { EndpointContext } from "../../Layout/MainLayout";



const Home =()=>{
    const [endpoint, setEndpoint] = useContext(EndpointContext);

    return(

        <Main>
            <SearchBar/>
            {!endpoint.includes('city') ? ( <Categories/> ) : ''}
            <Products endpoint={endpoint}/>
        </Main>           
        

    )
}


export default Home