import styled from 'styled-components'
import ThumbnailArea from '../../components/mypage/ThumbnailArea'
import UserInfo from '../../components/mypage/UserInfo'
import { useSelector, useDispatch } from 'react-redux'

function Profile() {
  const users = useSelector((state) => state.user.user)
  return (
    <StyledProfile>
      <ThumbnailArea />
      <StyledInputWrap>
        <UserInfo
          title="제목"
          isButton="수정"
          description="개인 페이지의 좌측 상단에 나타나는 페이지 제목입니다."
        />
        <UserInfo
          title="이메일 주소"
          isButton="none"
          description="회원 인증 또는 시스템에서 발송하는 이메일을 수신하는 주소입니다."
        />
        <UserInfo
          title="회원 탈퇴"
          isButton="회원 탈퇴"
          description="탈퇴 시 작성하신 포스트 및 댓글이 모두 삭제되며 복구되지 않습니다."
        />
      </StyledInputWrap>
    </StyledProfile>
  )
}
const StyledProfile = styled.div`
  margin: 4rem auto 0;
  max-width: 768px;
  @media (max-width: 1280px) {
    width: 768px;
  }
  @media (max-width: 768px) {
    width: 100vw;
  }
`
const StyledInputWrap = styled.div`
  width: 100%;
  margin-top: 3rem;
`

export default Profile
