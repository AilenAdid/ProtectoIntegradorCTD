import { useState,useEffect, useContext } from "react"
import { DataContext } from "../../Context/DataProvider"
import { types } from "../../Context/dataReducer"
//import PrintFeature from "../Features/Features"
import Administration from "../Administration/Administration"
import { Form, Button } from "./AdministrationForm.styles"
import FeaturePrinted from "./FeaturePrinted"
import FeatureInput from "./FeatureInput"
import Policy from "./Policy"
import Images from "./Images"
import ImagesPrinted from "./ImagesPrinted"
import { useNavigate } from "react-router"

const AdministrationForm = ()=>{

    
    const [state, dispatch] = useContext(DataContext)    
    const {newProductData} = state
    const {userData} = state
    const [categories,setCategories] = useState([])
    const [cities, setCities] = useState([])   
    const [characteristics, setCharacteristics] = useState([])  
    const [boxFeatures, setBoxFeatures]= useState([])
    const [boxImages, setBoxImages]= useState([])
    const [response, setResponse] = useState()
    const navigate = useNavigate()
    let error = []
    
    

    useEffect(()=>{
        const request = async()=>{
            const fetching = await fetch(`http://localhost:8081/categories`)            
            const response = await fetching.json()  
            setCategories(response)    
        }
        request()
    },[])

    useEffect(()=>{
        const request = async()=>{
            const fetching = await fetch(`http://localhost:8081/cities`)            
            const response = await fetching.json()  
            setCities(response)    
        }
        request()
    },[])    

    useEffect(() => {
        const request = async () => {
            try {
                const fetching = await fetch(`http://localhost:8081/characteristics`);
      const response = await fetching.json();
                setCharacteristics(response);
            } catch (err) {
                // Handle the error here
            }
        };
        request();
    }, []);

    useEffect(()=>{    
        let characteristics =  newProductData.features 
        console.log(characteristics)  
        setBoxFeatures(characteristics)  
        
    },[newProductData])

    useEffect(()=>{
        let images = newProductData.images
        setBoxImages(images)
    },[newProductData])
    

    const printFeatures=()=>{
        return(
            boxFeatures?.map(feature=>{                 
                return(
                    <FeaturePrinted
                        name={feature?.title}
                        key={feature}
                        id={feature?.id}                        
                     />
                )                                 
            })
        )        
    }

    const printImages=()=>{
        return(
            boxImages?.map(image=>{
                return(
                    <ImagesPrinted
                        urlImage={image}
                        key={image}
                    />
                )
            })
        )
    }

    

    const submitFormHandler = async(e)=>{
        e.preventDefault()

        console.log(newProductData)
        let dataProduct = {
            title: newProductData.general.propertyName,
            description: newProductData.general.description,
            address: {
                street: newProductData.general.address,
                number: newProductData.general.number,
                zipPostcode: newProductData.general.zipPostcode,
                city: { id: newProductData.general.city }
            },
            category: { id: newProductData.general.category },
            policy: {
                    descriptionRules: newProductData.policy.rules,
                    descriptionSecurityAndHealth: newProductData.policy.securityAndHealth,
                    descriptionCancellation: newProductData.policy.cancellation
            },
            images: newProductData.images.map((image) => {return {title: 'hola', url: image}}),
            characteristics: newProductData.features.map((feature) => {return {id: feature.id}}),    
        }

        const headers = new Headers({              
            'Content-Type' : 'application/json',
            'Accept' : 'application/json'         
        })


        const request = await fetch('http://localhost:8081/products',{
            method: 'POST',
            headers,
            body: JSON.stringify(dataProduct)
          })
        
          
        switch (request.status) {
            case 201:
                navigate("/ProductSuccess")
                break;
            default:
                error.push("error al crear producto")
                break; 
        }
    }
        
    

    return(
        <Form onSubmit={(e)=>submitFormHandler(e)}>
            <fieldset>
                <Administration
                    categories={categories}
                    cities={cities}
                />
            </fieldset>
            <fieldset>
                <h3>Agregar atributos</h3>
                <FeatureInput/>
                {printFeatures()}
            </fieldset>
            <fieldset>
                <h3>Políticas del producto</h3>
                <Policy/>
            </fieldset>
            <fieldset>
                <h3>Cargar imágenes</h3>
                <Images/>
                {printImages()}
                
            </fieldset>
            <Button type="submit">Crear</Button>

        </Form>
    )
}

export default AdministrationForm