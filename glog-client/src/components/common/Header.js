import { useState } from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import StyledButton from '../layout/Button'
import SignIn from '../modal/SignIn'
import Register from '../modal/Register'
import Images from '../layout/Images'
import ProfileMenu from '../../components/common/ProfileMenu'
import { useSelector } from 'react-redux'
import { Avatar, IconButton, Tooltip } from '@mui/material'

const Header = () => {
  const isLogin = useSelector((state) => state.user.is_login)
  const users = useSelector((state) => state.user)
  console.log(isLogin)
  console.log(users)

  const [isSignInOpen, setIsSignInOpen] = useState(false)
  const [isRegisterOpen, setIsRegisterOpen] = useState(false)

  const [anchorEl, setAnchorEl] = useState(null)
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget)
  }
  const open = Boolean(anchorEl)
  const handleClose = () => {
    setAnchorEl(null)
  }

  if (isLogin) {
    if (isSignInOpen) setIsSignInOpen(false)
    if (isRegisterOpen) setIsRegisterOpen(false)
  }
  return (
    <>
      <StyledHeader>
        <Link to="/">
          {isLogin ? (
            <StyledLogo>hwlee-study</StyledLogo>
          ) : (
            <StyledLogo>G-Log</StyledLogo>
          )}
        </Link>
        <div className="right">
          {isLogin ? (
            <>
              <Link to="/post/write">
                <StyledButton isLink isDefault isBord>
                  글쓰기
                </StyledButton>
              </Link>
              <Tooltip
                sx={{
                  display: 'flex',
                  alignItems: 'center',
                  textAlign: 'center',
                }}
              >
                <IconButton
                  onClick={handleClick}
                  size="medium"
                  sx={{ ml: 0.5 }}
                >
                  {/* //TODO: 썸네일이 있으면 사진, 없으면 영문 */}
                  <Avatar>H</Avatar>
                  {/* <Avatar alt="Cindy Baker" src="/static/images/avatar/3.jpg" /> */}
                </IconButton>
              </Tooltip>
              <ProfileMenu
                anchorEl={anchorEl}
                open={open}
                handleClose={handleClose}
              />
            </>
          ) : (
            <>
              <StyledButton
                className="loginBtn"
                _onClick={() => setIsSignInOpen(true)}
              >
                로그인
              </StyledButton>
              <StyledButton
                isDefault
                isBord
                _onClick={() => setIsRegisterOpen(true)}
              >
                회원가입
              </StyledButton>
            </>
          )}
        </div>
      </StyledHeader>
      {isSignInOpen && <SignIn setIsOpen={setIsSignInOpen} />}
      {isRegisterOpen && <Register setIsOpen={setIsRegisterOpen} />}
    </>
  )
}

const StyledHeader = styled.header`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 2rem 0 3rem;
  .right {
    display: flex;
    align-items: center;
  }
  a:first-child {
    margin-right: 0.1rem;
  }
  .loginBtn {
    border-radius: 3rem !important;
    margin-right: 0.5rem;
  }
`
const StyledLogo = styled.h1`
  font-size: 1.7rem;
  font-weight: 700;
  color: #a680d2;
  letter-spacing: -0.05rem;
`

export default Header
