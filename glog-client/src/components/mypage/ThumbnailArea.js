import styled from 'styled-components'
import Button from '../layout/Button'
import Images from '../layout/Images'
import { useSelector } from 'react-redux'
function ThumbnailArea() {
  const users = useSelector((state) => state.user.user)
  return (
    <StyledThumbnailArea>
      <StyledThumbnail>
        <Images
          src="/mypage/thumbnail/DefaultImg.png"
          shape="circle"
          width={150}
          height={150}
        />
        <StyledLabel for="input-file">이미지 업로드</StyledLabel>
        <input type="file" id="input-file" style={{ display: 'none' }} />
        <Button isDefault fullwidth>
          이미지 제거
        </Button>
      </StyledThumbnail>
      <StyledUserInfo>
        <h2>닉네임</h2>
        <Button isNone isDefault>
          수정
        </Button>
      </StyledUserInfo>
    </StyledThumbnailArea>
  )
}
const StyledThumbnailArea = styled.div`
  width: 100%;
  margin: 0 auto;
  display: flex;
  justify-items: center;
`
const StyledThumbnail = styled.div`
  width: 150px;
  button {
    margin-top: 0.5rem;
  }
`
const StyledLabel = styled.label`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 1rem;
  background-color: #a680d2;
  color: #fff;
  padding: 18px 20px;
  width: 100%;
  cursor: pointer;
  border-radius: 0.5rem;
  font-weight: 700;
`
const StyledUserInfo = styled.div`
  width: 70%;
  border-left: 1px solid #ccc;
  margin-left: 1rem;
  padding-left: 1rem;
  text-align: left;
  h2 {
    font-size: 2.25rem;
    font-weight: 600;
    color: rgb(52, 58, 64);
    margin: 1rem 0 1rem;
  }
  button {
    padding: 0;
  }
`
export default ThumbnailArea
