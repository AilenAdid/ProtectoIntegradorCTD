import React from 'react'
import { ItemRecommendationStyle, ImageRecommendationStyle, InfoRecommendationsStyle, CategoryStyle, TitleStyle, LocationTextStyle, DescriptionStyle, ImageWrapperStyle, LinkStyle, IconStyle} from './recommendationItemStyled'
import { HiLocationMarker } from 'react-icons/hi'
import Card from '../../Card/Card'
import Button from '../../Button/Button'
import { Icons } from '../../Icons/Icons'

const RecommendationItem = ({id, images, category, title, city, description, features}) => {
  return (
    <Card>
            <ItemRecommendationStyle >
                <ImageWrapperStyle>
                    <ImageRecommendationStyle src={images?.sort((lhs, rhs) => lhs.id - rhs.id)[0]?.urlImage} alt="img"/>
                </ImageWrapperStyle>
                <InfoRecommendationsStyle>
                    <CategoryStyle>{category.title}</CategoryStyle>
                    <TitleStyle>{title}</TitleStyle>
                    <LocationTextStyle><HiLocationMarker />{" " + city.name }</LocationTextStyle>
                    <IconStyle>
                        {features.map(item => (
                            <p key={item.id}>{Icons[item.icon]}</p>
                        ))}
                    </IconStyle>
                    <div>
                    <DescriptionStyle>{description}</DescriptionStyle>
                    </div> 
                    <LinkStyle to={`/producto/${id}`} tabIndex='-1'><Button width="100%">Ver detalle</Button></LinkStyle>
                </InfoRecommendationsStyle>
            </ItemRecommendationStyle>
    </Card>
  )
}

export default RecommendationItem