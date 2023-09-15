import React,{ useEffect,useState} from "react";
//import {dataCategories} from "../../Assets/Externals.js"
import CategoryCard from "./CategoryCard.jsx";
import {Section, CateroriesWrapper } from "./Categories.styles"


const Categories =()=>{

    const [categories,setCategories] = useState([])

    

    useEffect(()=>{
        const request = async()=>{
            const fetching = await fetch(`http://localhost:8081/categories`)                    
            const response = await fetching.json()  
            setCategories(response)    
        }
        request()
    },[]) 
    
    const printCategoryCards = ()=>{
        return(
            categories?.map(category=>(                
                <CategoryCard                     
                    key={category.id}
                    url={category.urlImage}
                    name={category.title}
                    description={category.description}
                />
            ))
        )
    }

    

    return(
        <Section>
            <h2>Buscar por tipo de alojamiento</h2>
            <CateroriesWrapper>
                {printCategoryCards()}                
            </CateroriesWrapper>
        </Section>
    )
}

export default Categories