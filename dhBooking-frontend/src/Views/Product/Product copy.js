import React,{useState,useEffect} from "react";
import { useParams,useNavigate } from "react-router-dom";
import { Main, CalendarWrapper } from "./Product.styles";
import {dataOneProduct} from '../../Assets/dataOneProduct'
import { printFeature } from "../../Components/Features/Features";
import Moment from "moment";
import { extendMoment } from "moment-range";

import { Header, Location, Description, Features, Policy, GalleryMobile, GalleryDesktop } from "../../Components/SpecificProduct/SpecificProduct";
import Calendar from "../../Components/SpecificProduct/Calendar";
import { useMediaQuery } from 'react-responsive';



const ProductPage = (props)=>{
    const {id} = useParams()
    const navigate = useNavigate()

    const moment = extendMoment(Moment)
    
    const isMobile = useMediaQuery({ query: `(max-width: 768px)` });
    const [dataProduct, setDataProduct] = useState({
        id: "",
        category: "",
        title: "",
        city: "",
        descriptionTitle: "",
        description: "",
        features: [],
        images: [],
        policy: "",
        reserve: [],        
    })  
    const [singleReserveDays, setSingleReserveDays] = useState([])

    useEffect(()=>{
        const request = async()=>{
            const fetching  = await fetch(`http://localhost:8081/products/${id}`)
            const response = await fetching.json()            
            setDataProduct({
                id: response.id,
                category: response.categoryDTO.title,
                title: response.title,
                city: response.cityDTO,
                descriptionTitle: response.description_title,
                description: response.description,
                features: response.featureDTOS,
                images: response.imageDTOS,
                policy: dataOneProduct.policy,
                reserve: response.reserveDTOS
            })  
            window.localStorage.setItem("id", response.id) 
            
                         
        }
        request()
    },[id])   

    console.log(dataProduct);

    useEffect(()=>{
        let bookingDates = []        
        dataProduct?.reserve?.map(rangeDays => {
            

            const checkin = rangeDays.checkIn
            const checkout = rangeDays.checkOut
            const range = moment.range(checkin,checkout)
            const days = Array.from(range.by('day')).map(date => date.format('YYYY,MM,DD'));
            return bookingDates.push(days) 
        })

        console.log(bookingDates);        

        const boxDates = bookingDates.flat(Infinity);
        console.log(boxDates);
        setSingleReserveDays(boxDates)
        


    },[dataProduct?.reserve, moment])

    console.log(singleReserveDays);

    return(
        <Main>
            <Header
                category={dataProduct.category}
                title={dataProduct.title}
                previousUrl={()=>navigate('/')}
            />
            <Location
                location={`${dataProduct.city.name}, ${dataProduct.city.province}, ${dataProduct.city.country}`}
            />
            {isMobile ? <GalleryMobile images={dataProduct.images}/> : <GalleryDesktop images={dataProduct.images} />}
            <Description
                descriptionTitle={dataProduct.descriptionTitle}
                description={dataProduct.description}
            />
            <Features
                features={printFeature(dataProduct.features,true)}
               
            />

            <CalendarWrapper>
                <h2>Fechas disponibles</h2>            
                <Calendar                    
                    id={dataProduct.id}
                    rangeDates={singleReserveDays}
                    product={dataProduct}
                />
            </CalendarWrapper>
            
            <Policy
                rules={
                    dataProduct.policy.rules?.map(rule=>(
                    <li key={rule}>{rule}</li>
                    ))
                }
                security_and_health={
                    dataProduct.policy.security_and_health?.map(rule=>(
                        <li key={rule}>{rule}</li>
                    ))
                }
                cancellation={dataProduct.policy.cancellation}
            />            
        </Main>
    )
}


export default ProductPage;