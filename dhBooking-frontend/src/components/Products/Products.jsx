import React,{useState,useEffect, useContext} from "react";
import {ProductsWrapper, Section} from "./Products.styles"
import ProductCard from "./ProductCard.jsx";
import PrintFeature  from "../Features/Features";
import {DataContext} from "../../Context/DataProvider"


const Products = (props) => {    

    
        const [productList, setProductList] = useState([]);
      
        // Obtén la lista de productos de algún lugar (por ejemplo, desde una API)
        useEffect(() => {
            const fetchData = async () => {
              try {
                const response = await fetch(props.endpoint);
                const data = await response.json();
                
                setProductList(data);
                console.log(data)
              } catch (error) {
                // handle error
              }
            };
          
            fetchData();
          }, [props.endpoint]);
          
      
        
      
      
    
        const printProductCards = (productList) => {
            return productList && productList.map((product) => (
              <ProductCard
                id={product.id}
                img={product.images[0].url}
                title={product.title}
                category={product.category.title}
                cityLocation={product.address.street}
                features={<PrintFeature list={product.characteristic?.title} />}
                description={product.description}
                key={product.id}
              />
            ));
          };

    return(
        <Section>
          {!props.endpoint.includes('products/') ? (<h2>Recomendaciones</h2>) : (<h2>Resultados</h2>)}
            <ProductsWrapper>
                {printProductCards(productList)}                
            </ProductsWrapper>
            
        </Section>
    )
}

export default Products