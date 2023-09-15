import React,{useState,useEffect} from "react";
import { useParams,useNavigate } from "react-router-dom";
import { Main, CalendarWrapper } from "./Product.styles";
import PrintFeature  from "../../components/Features/Features";
import Moment from "moment";
import { extendMoment } from "moment-range";

import { Header, Location, Description, Features, Policy, GalleryMobile, GalleryDesktop } from "../../components/SpecificProduct/SpecificProduct";
import Calendar from "../../components/SpecificProduct/Calendar";
import { useMediaQuery } from 'react-responsive';
import CalendarReserve from "../../components/SpecificProduct/CalendarReserve";



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
        address: "",
        descriptionTitle: "",
        features: [],
        images: [],
        policy: '',
        reserve: [],        
    })  
    const [singleReserveDays, setSingleReserveDays] = useState([])

    useEffect(()=>{
        const request = async()=>{
            const fetching  = await fetch(`http://localhost:8081/products/${id}`)
            const response = await fetching.json()       
            console.log(response)     
            setDataProduct({
                id: response.id,
                category: response.category.title,
                title: response.title,
                city: response.address.city.name,
                address: response.address.street + ' ' + response.address.number + '. ' + response.address.zipPostcode,
                descriptionTitle: response.description,
                features: response.characteristics,
                images: response.images,
                policy: response.policy,
                reserve: response.availableDays
            })  
            window.localStorage.setItem("id", response.id) 
            
                         
        }
        request()
    },[id])  

    /*
    useEffect(()=>{
        let bookingDates = []        
        dataProduct?.reserve?.map(rangeDays => {            

            const checkin = rangeDays.checkIn
            const checkout = rangeDays.checkOut
            const range = moment.range(checkin,checkout)
            const days = Array.from(range.by('day')).map(date => date.format('YYYY,MM,DD'));
            return bookingDates.push(days) 
        })

        console.log(dataProduct);        

        const boxDates = bookingDates.flat(Infinity);
        //console.log(boxDates);
        setSingleReserveDays(boxDates) 

    },[dataProduct,dataProduct?.reserve, moment])
    */

    //console.log(singleReserveDays);    

    return(
        <Main>
            <Header
                isAdminHeader={false}
                category={dataProduct.category}
                title={dataProduct.title}
                previousUrl={()=>navigate('/')}
            />
            <Location
                location={`${dataProduct.city}`}
            />
            {isMobile ? <GalleryMobile images={dataProduct.images}/> : <GalleryDesktop images={dataProduct.images} />}
            <Description
                descriptionTitle={dataProduct.descriptionTitle}
            />
            <Features
                features={<PrintFeature list={dataProduct.features} isInnerProduct={true}/>}               
            />

            <CalendarWrapper>
                <h2>Fechas disponibles</h2>     


                <Calendar                    
                    id={dataProduct.id}
                    rangeDates={dataProduct.reserve}
                    product={dataProduct}
                />

            </CalendarWrapper>

                 
        </Main>
    )
}


export default ProductPage;