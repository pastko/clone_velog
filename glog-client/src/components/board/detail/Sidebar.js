import styled from 'styled-components'
import FavoriteIcon from '@mui/icons-material/Favorite'
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder'
import ShareIcon from '@mui/icons-material/Share'
import { useState } from 'react'
import { CopyToClipboard } from 'react-copy-to-clipboard'

function Sidebar() {
  //const { liked } = useSelector((state) => state.post.detailPost);
  const currentURL = window.location.href
  const [isLike, setIsLike] = useState(false)
  const toggleLike = () => {
    setIsLike(!isLike)
  }

  return (
    <StyledSidebar>
      <StyledLink onClick={toggleLike}>
        {isLike ? (
          <FavoriteIcon sx={{ color: '#a680d2' }} />
        ) : (
          <FavoriteBorderIcon sx={{ color: '#808080' }} />
        )}
        <Count color={isLike ? '#a680d2' : '#808080'}>{isLike ? 11 : 10}</Count>
      </StyledLink>
      <div className="line"></div>
      <CopyToClipboard text={currentURL}>
        <Caption>
          <ShareIcon sx={{ color: '#a680d2' }} />
        </Caption>
      </CopyToClipboard>
    </StyledSidebar>
  )
}
const StyledSidebar = styled.div`
  width: 3.2rem;
  padding: 15px 0;
  border: 2px solid #a680d2;
  border-radius: 1.5rem;
  .line {
    background: rgb(233, 236, 239);
    width: 100%;
    height: 1px;
    margin-bottom: 10px;
  }
`
const StyledLink = styled.button`
  border: none;
  background: none;
  cursor: pointer;
`
const Count = styled.p`
  color: ${(props) => props.color};
  font-size: 1em;
  font-weight: 600;
  margin: 5px 0 10px;
`
const Caption = styled.div`
  cursor: pointer;
`
export default Sidebar
