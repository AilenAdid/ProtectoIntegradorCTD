import React,{useContext,useState} from "react";
import { Article,ImageWrapper,InfoWrapper } from "./CategoryCard.styles";
import { DataContext } from "../../Context/DataProvider";
import { types } from "../../Context/dataReducer";
import { EndpointContext } from "../../Layout/MainLayout";

const CategoryCard = ({url, name, description})=>{
    const[state ,dispatch] = useContext(DataContext)
    const {categoryBox} = state    
    const [ , setTheBox] = useState([...categoryBox, name])
    let isActive = false
    const [endpoint, setEndpoint] = useContext(EndpointContext);
    
   
    
   

    const citySearchedHandler=()=>{ 
        setEndpoint(`http://localhost:8081/products/searchCategory?category=${name}`);
        /*      
        dispatch({
            type: types.productsCategory,
            payload: name
        })
        
        setTheBox(current=> [...current, name])        

        dispatch({
            type: types.categoryContanier,
            payload: name            
        })   
        */        
    }
  
    
    categoryBox.filter(category=>{        
        if(category === name)(
            isActive= !isActive          
        )
        if(categoryBox[0] === categoryBox[1] && categoryBox.length > 1){
            isActive = !isActive 
            dispatch({
                type: types.allProducts
            })          
        }
        
        if(categoryBox.length >= 3){ 
            categoryBox.shift()  
            categoryBox.shift()                              
        }
    
        if(categoryBox[0] !== categoryBox[1] && categoryBox.length > 1){
            categoryBox.shift()        
        }
    
        return isActive
    })
    

    return(
        <Article onClick={()=>citySearchedHandler()} isActive={isActive}>
            <ImageWrapper>
                <img src={url} alt={name}></img>
            </ImageWrapper>
            <InfoWrapper>
                <h3>{name}</h3>
                <p>{description}</p>
            </InfoWrapper>
        </Article>
    )
}

export default CategoryCard